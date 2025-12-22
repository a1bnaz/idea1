import {Navigate, Outlet} from "react-router-dom";
import { useAuthStore } from "../store/authStore";

// the ProtectedRoute component acts as a "gate. this is a simple logic wrapper that says if you are logged in, it shows the page, if not, it sends you away- back to the login page.
function ProtectedRoute(){
    const isAuthenticated = useAuthStore((state) => state.isAuthenticated);

    // if not authenticated, redirect to Login
    // "replace" prevents the user from clicking 'back' to the protected page
    if(!isAuthenticated){
        return <Navigate to="/login" replace/>;
    }

    // if authenticated, render the child routes (the "outlet")
    // <Outlet /> is a placeholder and tells react router "if the user is allowed here, render whatever page they were trying to visit (dashboard, profile, etc.) right here"
    return <Outlet />
}

export default ProtectedRoute;