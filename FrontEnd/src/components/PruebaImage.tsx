import { useState, useEffect, useCallback } from "react";
import { useDropzone } from "react-dropzone";

import uploadSong from "/uploadSong.png";

export const ImageUpload = () => {
  const [image, setImage] = useState<File | null>(null);
  const [isImage, setIsImage] = useState<boolean>(false);
  const [preview, setPreview] = useState<string | null>(null);

  const onDrop = useCallback((acceptedFiles: File[]) => {
    const file = acceptedFiles[0];

    setImage(file);
    setIsImage(true);

    const objectUrl = URL.createObjectURL(file);
    setPreview(objectUrl);
  }, []);

  const { getRootProps, getInputProps, isDragActive, acceptedFiles } =
    useDropzone({ onDrop });

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(acceptedFiles[0]);
  };

  useEffect(() => {
    return () => {
      if (preview) URL.revokeObjectURL(preview);
    };
  }, [preview]);

  let content;

  if (isImage) {
    content = (
      <div className="w-48 h-48 p-4 border-2 text-white border-white rounded-xl flex flex-col items-center justify-center gap-2 cursor-pointer">
        <img src={preview} alt="imagen subida" className="w-[200px] rounded-xl"/>
      </div>
    );
  } else {
    content = (
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
    );
  }

  return (
    <div className="bg-[#1A192A]">
      <form onSubmit={handleSubmit}>
        {content}
        <button>Enviar</button>
      </form>
    </div>
  );
};
