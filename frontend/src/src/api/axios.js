import axios from "axios";
import {useAuthStore} from "@/stores/auth";

// 1. create an axios instance
// this is a custom copy of axios with your backend's base URL already set
const api = axios.create({baseURL: "http://localhost:8080/api"});

// 2. add the request interceptor
api.interceptors.request.use((config) => {
    // we reach into our Zustand store to get the token
    // note: we use .getState() because this is a regular JS file, not a react component!
    const token = useAuthStore.getState().token;

    if (token){
        // if the token exists, add it to the "authorization" folder
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;

    }, (error) => {
    return Promise.reject(error);
})

// response interceptor
api.interceptors.response.use(
    (response) => response, // if the request is successful, just pass it through
    (error) => {
        // if the server sends back a 401 (unauthorized)
        if (error.response && error.response.status === 401){
            console.log("token expired or invalid. logging out...")
            
            // clear the Zustand store (this also clears LocalStorage!)
            useAuthStore.getState().logout();
        }

        return Promise.reject(error);
    }
);

export default api;