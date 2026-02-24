import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import SingInPage from './pages/SingInPage.tsx'
import SingUpPage from './pages/SingUpPage.tsx'
import Home from './pages/home.tsx'
import './index.css'

const router = createBrowserRouter([
  {
    path: "/singin",
    element: <SingInPage />,
  },
  {
    path: "/singup",
    element: <SingUpPage />,
  },
  {
    path: "/home",
    element: <Home />,
  },
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
