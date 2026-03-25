type ButtonProps = {
  text: string;
};

export default function ButtonSubmit({text}:ButtonProps) {
  return (
    <button
      type="submit"
      className="w-full bg-[#864DBF] hover:bg-[#633295] text-black font-semibold py-2 rounded-lg transition-all duration-200 active:scale-95"
    >
      {text}
    </button>
  );
}
