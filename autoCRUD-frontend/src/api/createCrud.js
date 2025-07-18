import axios from 'axios';

const API_BASE_URL =  'http://localhost:8080/api';

export const createCrud = (payload) => {
    console.log("api called")
  return axios.get(`${API_BASE_URL}/createcrud`, payload);
};


