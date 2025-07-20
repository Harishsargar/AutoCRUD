import axios from 'axios';

const API_BASE_URL =  'http://localhost:8080/api/crud';

export const createCrud = (payload) => {
    console.log("api called")
  return axios.post(`${API_BASE_URL}/createcrud`, payload);
};


