import ListaVantagens from "./_components/ListaVantagens"

export default function EnviarMoeda() {
    return (
        <div className="p-6">
            <h1 className="text-2xl font-bold text-gray-800 mb-4">
                Vantagens Disponíveis Para Troca
            </h1>

            <p className="text-gray-600 mb-6">
                Aqui você pode visualizar e trocar suas moedas por vantagens disponíveis das nossas empresas parceiras.
            </p>

            <ListaVantagens />
        </div>
    );
}
