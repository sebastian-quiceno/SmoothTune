import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignUp = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [password2, setPassword2] = useState("");
  const [error, setError] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!email || !password) {
      setError("Por favor completa todos los campos");
      return;
    }

    if (password !== password2) {
      setError("Las contraseñas no coinciden");
      setPasswordError(true);
      return;
    }

    setPasswordError(false); // si todo está bien

    // 🔐 Aquí luego llamas a tu backend (Spring Boot)
    console.log({ email, password });

    // Simulación de login exitoso
    navigate("/home");
  };

  return (
    <div
      className="min-h-screen flex items-center justify-center bg-gray-900"
      style={{ backgroundImage: "url('/bandaTocando.png')" }}
    >
      <div className="absolute inset-0 bg-black/70"></div>
      <div className="w-full max-w-md bg-gray-800 rounded-2xl shadow-lg p-8 relative z-10 justify-center">
        <h2 className="text-3xl font-bold text-white text-center mb-6">
          Registrarse
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
            <label className="block text-gray-300 mb-1">
              Introduzca su contraseña
            </label>
            <input
              type="password"
              className="w-full px-4 py-2 rounded-lg bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-green-500"
              placeholder="********"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-gray-300 mb-1">
              Vuelva a introducir la contraseña
            </label>
            <input
              type="password"
              className={`w-full px-4 py-2 rounded-lg bg-gray-700 text-white focus:outline-none focus:ring-2 
								${ passwordError ? "border border-red-500 focus:ring-red-500": "focus:ring-green-500" }`}
              placeholder="********"
              value={password2}
              onChange={(e) => {
                setPassword2(e.target.value);
                setPasswordError(false); // se limpia al escribir
              }}
            />
          </div>

          <button
            type="submit"
            className="w-full bg-green-500 hover:bg-green-600 text-black font-semibold py-2 rounded-lg transition"
          >
            Registrar
          </button>
        </form>

        <p className="text-gray-400 text-sm text-center mt-6">
          ¿Ya tienes cuenta?{" "}
          <span
            className="text-green-400 cursor-pointer hover:underline"
            onClick={() => navigate("/singin")}
          >
            Iniciar Sesión
          </span>
        </p>
      </div>
    </div>
  );
};

export default SignUp;
