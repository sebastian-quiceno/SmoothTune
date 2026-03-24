import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { signIn } from "../services/authService";
import ButtonSubmit from '../components/ButtonSubmit'

const SignIn = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  //Evita recargar la pagina al hacer submit, lo maneja manualmente
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!email || !password) {
      setError("Por favor completa todos los campos");
      return;
    }

    // llamada a tu backend (Spring Boot)
    try {
      const response = await signIn({ email, password });

      localStorage.setItem("token", response.token);

      navigate("/home");
    } catch{
      setError("Email o contraseña incorrectos");
    }

  };

  return (
    <div
      className="min-h-screen flex items-center justify-center bg-gray-900"
      style={{ backgroundImage: "url('/bandaTocando.png')" }}
    >
      <div className="absolute inset-0 bg-black/70"></div>
      <div className="w-full max-w-md bg-[#1A192A] rounded-2xl shadow-lg p-8 relative z-10 justify-center">
        <h2 className="text-3xl font-bold text-white text-center mb-6">
          Iniciar sesión
        </h2>

        {error && (
          <p className="text-red-400 text-sm text-center mb-4">{error}</p>
        )}

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block text-gray-300 mb-1">Correo</label>
            <input
              type="email"
              className="w-full px-4 py-2 rounded-lg bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-green-500"
              placeholder="correo@ejemplo.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-gray-300 mb-1">Contraseña</label>
            <input
              type="password"
              className="w-full px-4 py-2 rounded-lg bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-green-500"
              placeholder="********"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <ButtonSubmit text="Entrar"/>
          
        </form>

        <p className="text-gray-400 text-sm text-center mt-6">
          ¿No tienes cuenta?{" "}
          <span
            className="text-[#58BDDE] cursor-pointer hover:underline"
            onClick={() => navigate("/singup")}
          >
            Regístrate
          </span>
        </p>
      </div>
    </div>
  );
};

export default SignIn;
