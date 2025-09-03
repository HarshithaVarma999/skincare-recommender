import axios from "axios";

// Create a reusable axios instance
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  withCredentials: false, // set to true if you use cookies/session auth
});

export default api;
