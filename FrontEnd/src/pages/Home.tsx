import logo from "/logo.png";
import generos from "/generos.jpg";
import escuchar from "/escuchar.jpg";
import buscar from "/buscar.jpg";
import microfono from "/microfono.jpeg";
import { Search, Music, Heart } from "lucide-react";
import Aurora from "./Aurora";
import Card from "../components/Card";
import Button from "../components/ButtonLogin";

function Home() {
  return (
    <div
      className="relative min-h-screen overflow-hidden bg-cover bg-center bg-no-repeat"
      style={{ backgroundImage: `url(${microfono})` }}
    >
      <div className="absolute inset-0 bg-[#0E0D1A]/90"></div>
      <div className="absolute inset-0 z-0 pointer-events-none [transform:scaleY(-1)]">
        <Aurora
          colorStops={["#4FF4FF", "#FF61FD", "#7118B8"]}
          blend={0.5}
          amplitude={1.0}
          speed={1}
        />
      </div>

      <div className="relative z-10">
        <header className="bg-[#0E0D1A] px-8 py-2 w-full h-15 flex flex-row items-center justify-between gap-4">
          <h1 className="text-4xl font-bold bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 bg-clip-text text-transparent">
            Smooth Tune
          </h1>
          <div className="p-2 flex justify-normal gap-2">
            <Button text="Iniciar Sesión" direccion="/singin" />
            <Button text="Registrarse" direccion="/singup" />
          </div>
        </header>

        <main className="flex flex-col items-center justify-center gap-2 m-6">
          <div className="flex flex-col items-center justify-center gap-2 max-w-lg">
            <img
              src={logo}
              alt="Vista previa del reproductor de música"
              className="w-[100px]"
            />
            <h3 className="text-2xl text-gray-200">Tu musica a tu ritmo</h3>
            <h2 className="text-3xl font-bold text-white text-center">
              Reproduce, organiza y descubre musica de forma sencilla y rapida.
            </h2>
          </div>
          <div className="flex flex-row gap-6 m-8">
            <Card
              icon={Music}
              text="Reproduce tus playlists"
              image={generos}
              alt="Generos musicales"
              color={true}
            />
            <Card
              icon={Heart}
              text="Guarda tus canciones favoritas"
              image={escuchar}
              alt="Canciones favoritas"
              color={false}
            />
            <Card
              icon={Search}
              text="Busca música fácilmente"
              image={buscar}
              alt="Busqueda"
              color={true}
            />
          </div>
        </main>
        <footer className="justify-between px-4 py-2 ">
          <p>©2026 Smoth Tune</p>
        </footer>
      </div>
    </div>
  );
}

export default Home;
