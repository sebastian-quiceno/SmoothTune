import { useState } from "react";
import {Info} from 'lucide-react'

type InfoCardProps = {
  title: string;
  artist: string;
  uploader: string;
  genre: string;
  duration: number;
  size: number;
  dateUpload: Date;
};

export const InfoCard = ({
  title,
  artist,
  uploader,
  genre,
  duration,
  size,
  dateUpload,
}: InfoCardProps) => {
	const [show, setShow] = useState<boolean>(false)
  return (
    <>
      
			<Info className="w-20 transition-transform duration-300 hover:scale-105 cursor-pointer" onClick={() => setShow(true)} />
      {show && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
          
          <div className="w-96 rounded-2xl bg-white p-6 shadow-2xl">
            <h2 className="mb-4 text-xl font-bold">
              Ventana emergente
            </h2>

            <p className="mb-6 text-gray-600">
              Esta tarjeta está sobre los demás elementos.
            </p>

            <button
              onClick={() => setShow(false)}
              className="rounded bg-red-500 px-4 py-2 text-white hover:bg-red-600"
            >
              Cerrar
            </button>
          </div>
        </div>
      )}
    </>
  );
};
