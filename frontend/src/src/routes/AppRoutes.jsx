import {Routes, Route, Navigate} from "react-router-dom";
import LoginPage from "../pages/LoginPage";
import Dashboard from "../pages/Dashboard";
import ProtectedRoute from "../components/ProtectedRoute";

function AppRoutes(){
    return(
        <Routes>
            {/* PUBLIC ROUTES */}
            <Route path="/login" element={<LoginPage/>}/>


            {/* PROTECTED ROUTES */}
            {/* we wrap these inside our ProtectedRoute component */}
            <Route element={<ProtectedRoute/>}>
                <Route path="/dashboard" element={<Dashboard/>}/>
                <Route path="/profile" element={<div>Profile Page (coming soon)</div>}/>
            </Route>
            

            {/* CATCH ALL */}
            {/* if the user types a random URL, send them to login or dashboard */}            <Route path="*" element={<Navigate to="/dashboard"/>}/>
        </Routes>
    )
}

export default AppRoutes;