import { useEffect, useState } from "react";
import api from "../api";
import SummaryCard from "../components/SummaryCard";

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

<div className="row">

<div className="col-md-6">

<div className="card shadow-sm">

<div className="card-body">

<h5>Total Value</h5>

<h1>

{summary.totalValue}

</h1>

</div>

</div>

</div>

</div>

</>

)}

<div className="card mt-5">

<div className="card-body">

<h3>

Upload CSV

</h3>

<input

className="form-control"

type="file"

onChange={(e)=>setFile(e.target.files[0])}

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