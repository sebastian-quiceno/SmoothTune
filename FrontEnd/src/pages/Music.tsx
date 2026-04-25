import { useEffect, useState } from "react";
import { Music, House, CassetteTape, ListMusic, MicVocal } from "lucide-react";

import NewSong from "../components/UploadSongButton";
import SectionButton from "../components/SectionButton";
import SearchBar from "../components/SearchBar";
import UserButton from "../components/UserButton";
import Greeting from "../components/Greetings";
import UploadedSongs from "../components/UploadedSongs";
import SongCard from '../components/SongCard'

import logo from "/logo.png";

//userId: string
const UserHome = () => {
  // const components = {
  //   home: <Home />,
  //   saved: <Saved />,
  //   uploaded: <Uploaded />,
  //   playlist: <Playlistx />,
  //   artist: <Artist />,
  // };

  const components = {
    home: "<Home />",
    saved: "<Saved />",
    uploaded: "<Uploaded />",
    playlist: "<Playlistx />",
    artist: "<Artist />",
  };

  const [sectionSelected, setSectionSelected] = useState(components.home);
  const [search, setSearch] = useState("");
  const canciones: string[] = ["Time", "Here And Now", "Graduation"];

  return (
    <div className="bg-[#2C2A4C] flex flex-row">
      <aside className="bg-gradient-to-b from-[#140E28] to-[#302551]  w-64 p-4 flex flex-col items-center">
        <div className="flex flex-col items-center">
          <div className="flex flex-row items-center gap-2">
            <img
              src={logo}
              alt="Vista previa del reproductor de música"
              className="w-[40px]"
            />
            <h1 className="text-2xl font-bold bg-gradient-to-r from-cyan-400 to-purple-600 bg-clip-text text-transparent">
              Smooth Tune
            </h1>
          </div>
          <NewSong />
        </div>
        <div className="p-4 gap-5 flex flex-col">
          <SectionButton
            Icon={House}
            direccion="asd"
            isSelect={sectionSelected === components.home ? true : false}
            onclick={() => setSectionSelected(components.home)}
            text="Inicio"
          />
          <SectionButton
            Icon={Music}
            direccion="asd"
            isSelect={sectionSelected === components.saved ? true : false}
            onclick={() => setSectionSelected(components.saved)}
            text="Canciones guardadas"
          />
          <SectionButton
            Icon={CassetteTape}
            direccion="asd"
            isSelect={sectionSelected === components.uploaded ? true : false}
            onclick={() => setSectionSelected(components.uploaded)}
            text="Canciones subidas"
          />
          <SectionButton
            Icon={ListMusic}
            direccion="asd"
            isSelect={sectionSelected === components.playlist ? true : false}
            onclick={() => setSectionSelected(components.playlist)}
            text="Playlists"
          />
          <SectionButton
            Icon={MicVocal}
            direccion="asd"
            isSelect={sectionSelected === components.artist ? true : false}
            onclick={() => setSectionSelected(components.artist)}
            text="Artistas"
          />
        </div>
      </aside>
      <section className="w-full">
        <header className="flex flex-row justify-between p-5 w-full">
          <SearchBar
            value={search}
            onChange={setSearch}
            results={canciones}
            loading={false}
            placeholder="Buscar Canciones..."
            keyExtractor={(p) => p}
            renderItem={(p) => p}
            onSelect={(item) => {
              console.log("Profesor seleccionado:", item);
            }}
          />
          <UserButton image="" username="User Name" />
        </header>
        <div className="flex flex-row justify-between">
          <Greeting image="" username="User Name" />
          <UploadedSongs uploadedSongs={25}/>
        </div>
        <hr className="mx-5 my-2 border-[#191527] border-2" />

        <div className="text-white font-bold">
          <span className="text-4xl">¿Que vas a escuchar hoy?</span>
        </div>

        <SongCard 
        autor="PinkFloid"
        img=""
        songName="Wish you where here"
        />


      </section>
    </div>
  );
};

export default UserHome;
