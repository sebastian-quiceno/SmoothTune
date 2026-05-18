import { AudioLines } from "lucide-react";

type UploadSongButtonProps = {
  onClick: () => void;
}

const UploadSongButton = ({ onClick }: UploadSongButtonProps) => {
  return (
    <button className="w-16 h-16 bg-[#8645FF] rounded-full flex items-center justify-center hover:scale-105 transition-all duration-200" onClick={onClick}>
        <AudioLines />
    </button>
  );
};

export default UploadSongButton