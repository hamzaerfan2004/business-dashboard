import { useState } from "react";
import api from "../api";

export default function Upload(){

    const[file,setFile]=useState();

    async function upload(){

        const form=new FormData();

        form.append("file",file);


        await api.post(

            "/api/uploads",

            form

        );

        alert("Upload Successful");

    }

    return(

        <div>

            <h2>Upload CSV</h2>

            <input
                type="file"
                onChange={(e)=>setFile(e.target.files[0])}
            />

            <br/>

            <button onClick={upload}>
                Upload
            </button>

        </div>

    );

}