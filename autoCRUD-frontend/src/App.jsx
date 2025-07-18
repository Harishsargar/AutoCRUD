import { useState } from 'react'
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { customRoutes } from "./routes";
import './App.css'

function App() {

  return (
    <>
      <Router>
      <Routes>
        {/* Unprotected Routes */}
        {customRoutes.map(({ path, element }) => (
          <Route key={path} path={path} element={element} />
        ))}

        {/* Fallback */}
        <Route path="*" element={<Navigate to={"/home"} />} />
      </Routes>
    </Router>
    </>
  )
}

export default App
