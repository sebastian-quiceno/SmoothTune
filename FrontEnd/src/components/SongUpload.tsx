import { useState, useRef } from "react";

type Props = {
  onFileSelect?: (file: File) => void;
};

export const SongUpload = ({ onFileSelect }: Props) => {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const [fileName, setFileName] = useState<string>(
    "Ningún archivo seleccionado",
  );

  const [selectedFile, setSelectedFile] = useState<boolean>(false);

  const handleClick = () => {
    inputRef.current?.click();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setSelectedFile(true);
      setFileName(file.name);
      onFileSelect?.(file);
    }
  };
  return (
    
    <div className={`w-[350px] flex items-center rounded-xl gap-4 ${selectedFile? "bg-green-300/70 " : "bg-white/10"}`}>
      <button
        type="button"
        onClick={handleClick}
        className="bg-[#E8E4E2] min-w-32 hover:bg-[#a6a2a1] w-32 px-4 py-2 rounded-lg transition"
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