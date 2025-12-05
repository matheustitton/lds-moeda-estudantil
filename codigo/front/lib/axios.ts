import axios from "axios";

const api = axios.create({
  baseURL: "http://48.217.82.183:8080/",
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

export default api;