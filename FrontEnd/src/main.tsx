import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import SingInPage from './pages/SingInPage.tsx'
import SingUpPage from './pages/SingUpPage.tsx'
import Home from './pages/Home.tsx'
import UserHome from './pages/Music.tsx'
import GenreForm from './pages/GenreForm.tsx'
import SongForm from './pages/SongForm.tsx'

import {Player} from './components/Player.tsx'
import {SongCardExtended} from './components/SongCardExtended.tsx'

import './index.css'

const router = createBrowserRouter([
  {
    path: "/signin",
    element: <SingInPage />,
  },
  {
    path: "/signup",
    element: <SingUpPage />,
  },
  {
    path: "/home",
    element: <Home />,
  },
  {
    path: "/userhome",
    element: <UserHome />,
  },
  {
    path: "/creategenre",
    element: <GenreForm />,
  },
  {
    path: "/upload-song",
    element: <SongForm />,
  },
  {
    path: "/prueba",
    element: <SongCardExtended id={1} title='kids with guns' artist='gorillaz' genre='rock' uploader='bafe' dateUpload={new Date("2026-05-17")} duration={226} size={0} />,
  },
  {
    path: "/player",
    element: <Player />,
  },
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
