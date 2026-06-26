import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "../css/Login.css";

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

    return (

    <div className="login-container">

        <div className="card shadow login-card">

            <div className="card-body p-4">

                <h2 className="text-center login-title">

                    Business Dashboard

                </h2>

                <p className="text-center login-subtitle">

                    Sign in to continue

                </p>

                <input
                    className="form-control mb-3"
                    placeholder="Email"
                    onChange={(e)=>setEmail(e.target.value)}
                />

                <input
                    className="form-control mb-3"
                    type="password"
                    placeholder="Password"
                    onChange={(e)=>setPassword(e.target.value)}
                />

                <button
                    className="btn btn-primary w-100"
                    onClick={login}
                >
                    Login
                </button>

                <p className="text-center mt-3">

                    Don't have an account?

                </p>

                <button
                    className="btn btn-outline-secondary w-100"
                    onClick={() => window.location="/register"}
                >

                    Register

                </button>

            </div>

        </div>

    </div>

    );
}