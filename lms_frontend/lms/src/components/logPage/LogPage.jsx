import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LogPage.css";

const LogPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // Use navigate from the hook

  const handleLogin = async () => {
    if (username && password) {
      try {
        const response = await fetch("http://localhost:8080/student/log", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email: username, // Map username to email field
            password: password,
          }),
        });

        if (response.ok) {
          const data = await response.json();
          alert(`Login successful! Token: ${data.token}`);
          // Save the token for future use
          localStorage.setItem("token", data.token);
          navigate("/dashboard"); // Correctly navigate to the dashboard
        } else {
          const errorData = await response.json();
          alert(`Login failed: ${errorData.error || "Invalid credentials"}`);
        }
      } catch (error) {
        console.error("Error logging in:", error);
        alert("An error occurred while logging in.");
      }
    } else {
      alert("Please enter both username and password!");
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <div className="form-group">
        <label htmlFor="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Enter your username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Enter your password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button onClick={handleLogin}>Log In</button>
    </div>
  );
};

export default LogPage;
