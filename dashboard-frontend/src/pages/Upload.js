import { useEffect, useRef, useState } from "react";
import api from "../api";
import SummaryCard from "../components/SummaryCard";
import CategoryBarChart from "../components/CategoryBarChart";
import CategoryPieChart from "../components/CategoryPieChart";
import { jwtDecode } from "jwt-decode";

export default function Upload() {

    const [userEmail, setUserEmail] = useState("");
    const [file, setFile] = useState();
    const [summary, setSummary] = useState(null);
    const [categories, setCategories] = useState([]);
    const [uploads, setUploads] = useState([]);
    const [search, setSearch] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);
    const fileInputRef = useRef(null);
    const [messageType, setMessageType] = useState("success");

    async function loadSummary() {

        const response = await api.get("/api/dashboard/summary");

        setSummary(response.data);

    }

    async function loadCategories() {

        const response = await api.get("/api/dashboard/category-summary");

        setCategories(response.data);

    }

    async function loadUploads() {

        const response =
            await api.get("/api/dashboard/uploads");

        setUploads(response.data);

    }

    async function searchUploads(value) {

        setSearch(value);

        if (value.trim() === "") {
            await loadUploads();
            return;
        }

        const response = await api.get(
            `/api/dashboard/uploads/search?filename=${value}`
        );

        setUploads(response.data);

    }

    useEffect(() => {

        loadSummary();
        loadCategories();
        loadUploads();

    }, []);

    useEffect(() => {

        if (!message) return;

        const timer = setTimeout(() => {

            setMessage("");

        }, 3000);

        return () => clearTimeout(timer);

    }, [message]);

    useEffect(() => {

        const token = localStorage.getItem("token");

        if (!token) {

            window.location = "/";
            return;

        }

        const decoded = jwtDecode(token);

        setUserEmail(decoded.sub);

    }, []);

    async function upload() {

        if (!file) {
            setMessageType("warning");
            setMessage("Please choose a CSV file.");
            return;
        }
        if (!file.name.toLowerCase().endsWith(".csv")) {
            setMessageType("warning");
            setMessage("Only CSV files are allowed.");
            return;
        }

        try {

            setLoading(true);

            const form = new FormData();

            form.append("file", file);

            await api.post("/api/uploads", form);

            setFile(null);
            fileInputRef.current.value = "";

            setMessageType("success");
            setMessage("Upload completed successfully.");

            await loadSummary();
            await loadCategories();
            await loadUploads();

        } catch (error) {

            setMessageType("danger");

            if (error.response?.data) {
                setMessage(error.response.data);
            } else {
                setMessage("Upload failed.");
            }

        } finally {

            setLoading(false);

        }

    }

    async function deleteUpload(id) {

        if (!window.confirm("Delete this upload?")) {
            return;
        }

        try {

            await api.delete(`/api/dashboard/uploads/${id}`);

            setMessageType("success");
            setMessage("Upload deleted successfully.");

            await loadSummary();
            await loadCategories();
            await loadUploads();

        } catch (error) {

            setMessageType("danger");
            setMessage("Delete failed.");

        }

    }

    function logout() {

        if (window.confirm("Are you sure you want to logout?")) {

            localStorage.removeItem("token");

            window.location = "/";

        }

    }

    return (

        <div className="container mt-5">

            <div className="d-flex justify-content-between align-items-center mb-5">
                <div>
                    <h1>Business Dashboard</h1>

                    <p className="text-muted">
                        Logged in ad {userEmail}
                    </p>
                </div>
                <button
                    className="btn btn-outline-danger"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

            {summary && (

                <>

                    <div className="row">

                        <SummaryCard
                            title="Total Uploads"
                            value={summary.totalUploads}
                        />

                        <SummaryCard
                            title="Processed"
                            value={summary.processedUploads}
                        />

                        <SummaryCard
                            title="Pending"
                            value={summary.pendingUploads}
                        />

                        <SummaryCard
                            title="Records"
                            value={summary.totalRecords}
                        />

                    </div>

                    <div className="row mt-4">

                        <div className="col-md-6">

                            <div className="card shadow-sm">

                                <div className="card-body">

                                    <h5>Total Value</h5>

                                    <h1>{summary.totalValue}</h1>

                                </div>

                            </div>

                        </div>

                    </div>

                </>

            )}

            <div className="card mt-4">

                <div className="card-body">

                    <h3>Category Summary</h3>

                    <table className="table table-striped">

                        <thead>

                            <tr>

                                <th>Category</th>
                                <th>Total</th>

                            </tr>

                        </thead>

                        <tbody>

                            {categories.map((category) => (

                                <tr key={category.category}>

                                    <td>{category.category}</td>

                                    <td>{category.total.toFixed(2)}</td>

                                </tr>

                            ))}

                        </tbody>

                    </table>

                </div>

            </div>
            <br></br>
            <div className="mb-3">

                <input
                    className="form-control"
                    placeholder="Search uploads..."
                    value={search}
                    onChange={(e) => searchUploads(e.target.value)}
                />

            </div>

            <div className="card mt-4">

                <div className="card-body">

                    <h3>Recent Uploads</h3>

                    <table className="table table-striped">

                        <thead>

                            <tr>

                                <th>Filename</th>

                                <th>Status</th>

                                <th>Uploaded</th>

                                <th>Action</th>

                            </tr>

                        </thead>

                        <tbody>

                            {uploads.map(upload => (

                                <tr key={upload.id}>

                                    <td>{upload.filename}</td>

                                    <td>

                                        {upload.status === "processed" ? (

                                            <span className="badge bg-success">
                                                Processed
                                            </span>

                                        ) : upload.status === "failed" ? (

                                            <span className="badge bg-danger">
                                                Failed
                                            </span>

                                        ) : (

                                            <span className="badge bg-warning text-dark">
                                                Pending
                                            </span>

                                        )}

                                    </td>

                                    <td>

                                        {new Date(upload.uploadDate).toLocaleString()}

                                    </td>

                                    <td>

                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={() => deleteUpload(upload.id)}
                                        >
                                            Delete
                                        </button>

                                    </td>

                                </tr>

                            ))}

                        </tbody>

                    </table>

                </div>

            </div>

            <div className="row mt-5">

                <div className="col-md-6">

                    <div className="card shadow-sm">

                        <div className="card-body">

                            <h4>Category Totals (Bar Chart)</h4>

                            <CategoryBarChart data={categories} />

                        </div>

                    </div>

                </div>

                <div className="col-md-6">

                    <div className="card shadow-sm">

                        <div className="card-body">

                            <h4>Category Distribution</h4>

                            <CategoryPieChart data={categories} />

                        </div>

                    </div>

                </div>

            </div>

            {message && (

                <div className={`alert alert-${messageType} mt-3`}>

                    {message}

                </div>

            )}

            <div className="card mt-5">

                <div className="card-body">

                    <h3>Upload CSV</h3>

                    <input
                        ref={fileInputRef}
                        className="form-control"
                        type="file"
                        accept=".csv"
                        onChange={(e) => setFile(e.target.files[0])}
                    />

                    <button
                        className="btn btn-primary mt-3"
                        onClick={upload}
                        disabled={!file || loading}
                    >
                        {loading ? "Uploading..." : "Upload"}
                    </button>

                </div>

            </div>
            <footer className="text-center text-muted mt-5 mb-4">

                Business Dashboard © 2026

                <br />

                Built with React, Spring Boot & MySQL

            </footer>
        </div>
        

    );

}