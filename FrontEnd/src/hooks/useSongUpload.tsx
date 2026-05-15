import { useState, useRef } from "react";

export const useSongUpload = () => {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const [fileName, setFileName] = useState<string>(
    "Ningún archivo seleccionado",
  );

  const handleClick = () => {
    inputRef.current?.click();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFileName(file.name);
    }
  };

  return (
    <div className="flex items-center gap-4">
      <button
        type="button"
        onClick={handleClick}
        className="bg-[#E8E4E2] hover:bg-[#E8E4E2] px-4 py-2 rounded-lg transition"
      >
        Subir archivo
      </button>

      <span className="text-gray-300 text-sm truncate max-w-[200px]">
        {fileName}
      </span>

      <input
        type="file"
        accept=".mp3"
        ref={inputRef}
        onChange={handleChange}
        className="hidden"
      />
    </div>
  );
};
