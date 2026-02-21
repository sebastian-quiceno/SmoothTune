import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import SingInPage from './pages/SingInPage.tsx'
import SingUpPage from './pages/SingUpPage.tsx'
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
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
