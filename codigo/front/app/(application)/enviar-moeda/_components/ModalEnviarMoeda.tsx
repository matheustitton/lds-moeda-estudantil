import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { useForm } from "react-hook-form"
import { z } from "zod"
import { zodResolver } from "@hookform/resolvers/zod"
import { AlunoResponse } from "@/types/Usuario/usuario.response"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { useEffect } from "react"
import { TransacaoRequisicao } from "@/server/Transacao"
import { TransacaoRequestDTO } from "@/types/Transacao/transacao.request"
import { toast } from "sonner"
import { useQueryClient } from "@tanstack/react-query"
import { useUsuario } from "@/contexts/user.context"

interface Props {
  open: boolean
  idProfessor: number
  aluno: AlunoResponse
  onClose: () => void
}

// Schema de validação com Zod
export const enviarMoedaSchema = z.object({
  aluno: z
    .string()
    .min(1, { message: "Selecione o aluno" }),

  valor: z
    .string()
    .refine(val => !isNaN(Number(val.replace(",", "."))) && Number(val.replace(",", ".")) > 0, {
      message: "Informe um número válido maior que zero",
    }),

  motivo: z
    .string()
    .min(1, { message: "Informe o motivo" }),
})

type FormValues = z.infer<typeof enviarMoedaSchema>

export default function ModalEnviarMoeda({ open, idProfessor, aluno, onClose }: Props) {
    const queryClient = useQueryClient()
    const {refetchUsuario} = useUsuario()
  const form = useForm<FormValues>({
    resolver: zodResolver(enviarMoedaSchema),
    defaultValues: {
        aluno: aluno.nome,
        valor: "0",
        motivo: "",
    }
  })

  useEffect(() => {
    form.reset()
  },[open])

  const handleSubmit = async (data: FormValues) => {
    const body: TransacaoRequestDTO = {
        motivo: data.motivo,
        valor: Number(data.valor),
        idAluno: aluno.id,
        idProfessor
    }
    
    await TransacaoRequisicao.EnviarMoedas(body).then((response) => {
        if(response.statusCode == 200) {
            toast.success("Moedas transferidas com sucesso!");
            refetchUsuario()
            queryClient.invalidateQueries()
            onClose()
        }
        else{
            toast.error("Erro ao enviar moedas\n" + response.error);
        }
    })
  }

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Enviar moedas para {aluno.nome}</DialogTitle>
        </DialogHeader>
        <Form {...form}>
            
        <form onSubmit={form.handleSubmit(handleSubmit)} className="space-y-4">
          <div className="flex flex-col gap-y-3">
            <FormField
                control={form.control}
                name="aluno"
                render={({ field }) => (
                <FormItem className="col-span-4">
                    <FormLabel className="flex items-center gap-2">Aluno</FormLabel>
                    <FormControl>
                    <Input
                        type="text"
                        value={field.value}
                        onChange={field.onChange}
                        disabled
                    />
                    </FormControl>
                    <FormMessage />
                </FormItem>
                )}
            />
            <FormField
                control={form.control}
                name="valor"
                render={({ field }) => (
                    <FormItem className="col-span-4">
                    <FormLabel className="flex items-center gap-2">Valor</FormLabel>
                    <FormControl>
                        <Input
                        type="text"
                        value={field.value?.toString() ?? ""}
                        onChange={(e) => field.onChange(e.target.value)} // <-- mantém string no form
                        placeholder="Digite o valor a enviar"
                        />
                    </FormControl>
                    <FormMessage />
                    </FormItem>
                )}
            />
            <FormField
                control={form.control}
                name="motivo"
                render={({ field }) => (
                <FormItem className="col-span-4">
                    <FormLabel className="flex items-center gap-2">Motivo</FormLabel>
                    <FormControl>
                    <Input
                        type="text"
                        value={field.value}
                        onChange={field.onChange}
                    />
                    </FormControl>
                    <FormMessage />
                </FormItem>
                )}
            />
          </div>

          <DialogFooter>
            <Button type="button" variant="outline" onClick={onClose}>
              Cancelar
            </Button>
            <Button type="submit">Enviar</Button>
          </DialogFooter>
        </form>
        </Form>
      </DialogContent>
    </Dialog>
  )
}