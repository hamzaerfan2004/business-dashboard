import { useState } from "react";
import axios from "axios";

export default function Login() {

    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");

    async function login(){

        const response=await axios.post(
            "http://localhost:8080/api/auth/login",
            {
                email,
                password
            }
        );

        localStorage.setItem("token", response.data.token);

        window.location="/upload";
    }

    return(

        <div>

            <h1>Business Dashboard</h1>

            <input
                placeholder="Email"
                onChange={(e)=>setEmail(e.target.value)}
            />

            <br/>

            <input
                type="password"
                placeholder="Password"
                onChange={(e)=>setPassword(e.target.value)}
            />

            <br/>

            <button onClick={login}>
                Login
            </button>

        </div>

    );
}