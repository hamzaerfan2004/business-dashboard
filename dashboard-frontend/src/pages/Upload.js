import { useEffect, useState } from "react";
import api from "../api";

export default function Upload() {

    const [file, setFile] = useState();

    const [summary, setSummary] = useState(null);

    async function loadSummary() {

        const response = await api.get("/api/dashboard/summary");

        setSummary(response.data);

    }

    useEffect(() => {

        loadSummary();

    }, []);

    async function upload() {

        const form = new FormData();

        form.append("file", file);

        await api.post("/api/uploads", form);

        alert("Upload Successful");

        loadSummary();

    }

    return (

        <div>

            <h1>Business Dashboard</h1>

            {summary && (

                <div>

                    <h3>Dashboard Summary</h3>

                    <p>Total Uploads: {summary.totalUploads}</p>

                    <p>Processed Uploads: {summary.processedUploads}</p>

                    <p>Pending Uploads: {summary.pendingUploads}</p>

                    <p>Total Records: {summary.totalRecords}</p>

                    <p>Total Value: {summary.totalValue}</p>

                </div>

            )}

            <hr />

            <h2>Upload CSV</h2>

            <input
                type="file"
                onChange={(e) => setFile(e.target.files[0])}
            />

            <br /><br />

            <button onClick={upload}>
                Upload
            </button>

        </div>

    );

}