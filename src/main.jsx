import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import ProfileManagementPage from './routes/ProfileManagementPage.jsx';
import FuelQuoteHistoryPage from './routes/FuelQuoteHistoryPage.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello world!</div>,
  },
  {
    path: "/ProfileManagement",
    element: <ProfileManagementPage />,
  },
  {
    path: "/FuelQuoteHistory",
    element: <FuelQuoteHistoryPage />
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
