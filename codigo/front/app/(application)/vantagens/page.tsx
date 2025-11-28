import ListaVantagens from "../../vantagens/_components/ListaVantagens";

export default function VantagensPage() {
    return (
        <div className="font-sans min-h-screen bg-gray-50 p-6 sm:p-12 flex flex-col items-center">
            {/* Título */}
            <h1 className="text-3xl sm:text-4xl font-bold text-primary mb-8 text-center sm:text-left">
                Vantagens Cadastradas - Minha Empresa
            </h1>

            {/* Lista de Vantagens */}
            <div className="w-full max-w-7xl">
                <ListaVantagens />
            </div>
        </div>
    );
}
