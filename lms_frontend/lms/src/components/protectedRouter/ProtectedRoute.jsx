import { Navigate } from "react-router-dom";

const ProtectedRoute = ({children}) => {
  const isLoggedIn = localStorage.getItem("token") !== null; // Check if user is logged in
  return isLoggedIn ? children : <Navigate to="/log" />;
};

export default ProtectedRoute;
