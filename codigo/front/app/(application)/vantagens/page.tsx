import ListaVantagens from "./_components/ListaVantagens";

export default function EnviarMoeda() {
    return (
        <div className="p-6">
            <h1 className="text-2xl font-bold text-gray-800 mb-4">
                Vantagens Disponíveis
            </h1>

            <p className="text-gray-600 mb-6">
                Aqui você pode visualizar, cadastrar e gerenciar todas as vantagens disponíveis para os alunos.
            </p>

            <ListaVantagens />
        </div>
    );
}
