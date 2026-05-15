import { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useGenres } from "../hooks/useGenres";
import { useDropzone } from "react-dropzone";

import ButtonSubmit from "../components/ButtonSubmit";

import genre from "/genre.jpg";

const UploadSong = () => {
  const navigate = useNavigate();

  const [tittle, setTittle] = useState("");
  const [artist, setArtist] = useState("");
  const [userId, setUserId] = useState("");
  const [genreId, setGenreId] = useState("");
};

const GenreForm = () => {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [formError, setFormError] = useState<string | null>(null);
  const onDrop = useCallback((acceptedFiles: File[]) => {
    console.log(acceptedFiles[0]);
  }, []);
  const { getRootProps, getInputProps, isDragActive, acceptedFiles } =
    useDropzone({ onDrop });

  const { createGenre, loading, error: apiError } = useGenres();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // 1. Resetear errores
    setFormError(null);

    // 2. Validación del lado cliente
    if (!name.trim()) {
      setFormError("El nombre es obligatorio");
      return;
    }

    if (name.length < 3) {
      setFormError("El nombre debe tener al menos 3 caracteres");
      return;
    }
    if (!description.trim()) {
      setFormError("La descripción es obligatoria");
      return;
    }

    await createGenre({ name, description });

    // (opcional) limpiar si no hay error del backend
    if (!apiError) {
      setName("");
      setDescription("");
    }
  };

  if (loading) return <p>Cargando...</p>;
  if (apiError) return <p>{apiError}</p>;

  return (
    <div
      className="min-h-screen flex items-center justify-center bg-gray-900"
      style={{
        backgroundImage: `url(${genre})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="absolute inset-0 bg-black/70"></div>
      <div className="w-full max-w-md bg-[#192a29] rounded-2xl shadow-lg p-8 relative z-10 justify-center">
        <h2 className="text-3xl font-bold text-white text-center mb-6">
          ¡Crea tu genero de musica!
        </h2>

        {formError && (
          <p className="text-red-400 text-sm text-center mb-4">{formError}</p>
        )}

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block text-gray-300 mb-1">
              Nombre del genero
            </label>
            <input
              type="text"
              className="w-full px-4 py-2 rounded-lg bg-[#364747] text-white focus:outline-none focus:ring-2 focus:ring-green-500"
              placeholder="ingrese el nombre del genero musical"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-gray-300 mb-1">Descripción</label>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Ingresa de que trata el genero..."
              rows={4}
              className="w-full px-4 py-2 rounded-lg bg-[#364747] text-white focus:outline-none focus:ring-2 focus:ring-green-500"
            />
          </div>
          <div
            className="w-48 h-48 p-4 border-2 border-dashed text-white border-white rounded-xl flex flex-col items-center justify-center gap-2 cursor-pointer"
            {...getRootProps()}
          >
            <img className="w-16 h-16" src={uploadSong} alt="uploadSong" />
            <input {...getInputProps()} />
            {isDragActive ? (
              <p>Drop the files here ...</p>
            ) : (
              <div className="flex flex-col items-center text-center gap-1">
                <span className="text-base">
                  Seleccione o arrastre su imagen aqui
                </span>

                <span className="text-xs text-gray-500">png jpg jpeg</span>
              </div>
            )}
          </div>

          <ButtonSubmit text="Crear Genero" color="orange" />
        </form>

        <span
          className="text-[#58BDDE] cursor-pointer hover:underline flex justify-center mt-2"
          onClick={() => navigate("/userhome")}
        >
          Volver al inicio
        </span>
      </div>
    </div>
  );
};

export default GenreForm;
