import { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "../css/Login.css";

export default function Register() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [messageType, setMessageType] = useState("success");
    const [loading, setLoading] = useState(false);

    useEffect(() => {

        if (!message) return;

        const timer = setTimeout(() => {

            setMessage("");

        }, 3000);

        return () => clearTimeout(timer);

    }, [message]);

    async function register() {

        if (!name || !email || !password) {

            setMessageType("warning");
            setMessage("Please complete all fields.");

            return;

        }

        try {

            setLoading(true);

            await axios.post(
                "http://localhost:8080/api/auth/register",
                {
                    name,
                    email,
                    password
                }
            );

            setMessageType("success");
            setMessage("Registration successful!");

            setTimeout(() => {

                window.location = "/";

            }, 1500);

        } catch (error) {

            setMessageType("danger");

            if (error.response?.data?.message) {

                setMessage(error.response.data.message);

            } else {

                setMessage("Registration failed.");

            }

        } finally {

            setLoading(false);

        }

    }

    return (

        <div className="login-container">

            <div className="card shadow login-card">

                <div className="card-body p-4">

                    <h2 className="text-center login-title">

                        Create Account

                    </h2>

                    <p className="text-center login-subtitle">

                        Register for Business Dashboard

                    </p>

                    <input
                        className="form-control mb-3"
                        placeholder="Name"
                        onChange={(e)=>setName(e.target.value)}
                    />

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

                    {message && (

                        <div className={`alert alert-${messageType}`}>

                            {message}

                        </div>

                    )}

                    <button
                        className="btn btn-success w-100"
                        onClick={register}
                        disabled={loading}
                    >
                        {loading ? "Creating account..." : "Register"}

                    </button>

                </div>

            </div>

        </div>

    );

}