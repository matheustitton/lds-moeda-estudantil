export default function Section() {
  return (
    <section className="flex flex-1 flex-col items-center h-max align-middle justify-center text-center px-4 mt-24">
      <h2 className="text-4xl md:text-5xl font-extrabold text-[#1E3A8A] mb-6">
        Recompense o mérito acadêmico com <span className="text-[#7B1E3A]">EducaCoins</span>
      </h2>

      <p className="text-lg text-gray-600 max-w-2xl mb-8">
        Uma plataforma inovadora que valoriza o esforço dos estudantes por meio
        de moedas digitais trocáveis por benefícios reais.
      </p>

      <button className="bg-[#1E3A8A] text-white px-8 py-3 rounded-full font-semibold text-lg hover:bg-[#7B1E3A] transition">
        Começar Agora
      </button>
    </section>
  )
}
