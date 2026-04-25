type UploadSongButtonProp = {
  uploadedSongs: number;
};

const UploadSongButton = ({uploadedSongs}:UploadSongButtonProp) => {
  return (
    <div className="p-2 flex flex-col items-end gap-2 text-white">
      <span className="text-white/60 text-2xl">Canciones subidas</span>
      <span className="font-bold text-2xl">{uploadedSongs}</span>
    </div>
  );
};

export default UploadSongButton;
