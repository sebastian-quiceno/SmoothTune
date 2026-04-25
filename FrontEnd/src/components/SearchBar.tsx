import { useState } from "react";

import { Search } from "lucide-react";

type SearchBarProps = {
  value: string;
  onChange: (direccion: string) => void;
  results: string[];
  loading: boolean;
  placeholder: string;
  renderItem: (direccion: string) => string;
  onSelect: (direccion: string) => void;
  keyExtractor: (direccion: string) => string;
};

export const SearchBar = ({
  value,
  onChange,
  results = [],
  loading = false,
  placeholder = "Buscar...",
  renderItem,
  onSelect,
  keyExtractor,
}: SearchBarProps) => {
  const [isFocused, setIsFocused] = useState(false);
  const showResults = isFocused && value.trim() !== "" && results.length > 0;
  return (
    <div
      //className="flex flex-row max-w-sm w-full px-2 py-2.5 gap-2 rounded-xl bg-white/10 backdrop-blur-md text-white  hover:bg-white/20 hover:border-white/40 hover:ring-2 hover:ring-white/20 transition-all duration-300"
      className={`flex flex-col justify-center max-w  rounded-xl backdrop-blur-md text-white  ${isFocused ? "bg-white/10 border-white/40 ring-2 ring-white/20" : "bg-white/15"} transition-all duration-300`}
    >
      <div className="flex flex-row px-2 gap-2 items-center">
        <Search />
        <input
          type="text"
          value={value}
          onChange={(e) => onChange(e.target.value)}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          placeholder={placeholder}
          className="
            bg-white/0
          placeholder:text-white/50
            outline-none ring-0
            border-b-black
            
          "
        />
      </div>

      {loading && isFocused && (
        <p className="text-sm text-gray-400 mt-1">Cargando...</p>
      )}

      {showResults && (
        <>
          <hr className="my-2 border-white/40" />
          <ul className="max-w-sm mt-1 max-h-48 overflow-y-auto">
            {results.map((item) => (
              <li
                key={keyExtractor(item)}
                onClick={() => onSelect(item)}
                className="p-2 gap-2 flex flex-row text-white/60 hover:bg-white/20 cursor-pointer"
              >
                <Search />
                {renderItem(item)}
              </li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
};

export default SearchBar;
