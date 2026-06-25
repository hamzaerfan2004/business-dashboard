import { useEffect, useState } from "react";
import api from "../api";
import SummaryCard from "../components/SummaryCard";
import CategoryBarChart from "../components/CategoryBarChart";
import CategoryPieChart from "../components/CategoryPieChart";

export default function Upload() {

    const [file, setFile] = useState();
    const [summary, setSummary] = useState(null);
    const [categories, setCategories] = useState([]);

    async function loadSummary() {

        const response = await api.get("/api/dashboard/summary");

        setSummary(response.data);

    }

    async function loadCategories() {

        const response = await api.get("/api/dashboard/category-summary");

        setCategories(response.data);

    }

    useEffect(() => {

        loadSummary();
        loadCategories();

    }, []);

    async function upload() {

        const form = new FormData();

        form.append("file", file);

        await api.post("/api/uploads", form);

        alert("Upload Successful");

        loadSummary();
        loadCategories();

    }

    return (

        <div className="container mt-5">

            <h1 className="text-center mb-5">
                Business Dashboard
            </h1>

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

            <div className="card mt-5">

                <div className="card-body">

                    <h3>Upload CSV</h3>

                    <input
                        className="form-control"
                        type="file"
                        onChange={(e) => setFile(e.target.files[0])}
                    />

                    <button
                        className="btn btn-primary mt-3"
                        onClick={upload}
                    >
                        Upload
                    </button>

                </div>

            </div>

        </div>

    );

}