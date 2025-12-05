'use client'

import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQuery, useQueryClient } from '@tanstack/react-query'
import { Form, FormField, FormItem, FormLabel, FormControl, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Button } from '@/components/ui/button'
import api from '@/lib/axios'
import { InstituicaoEnsinoResponse } from '@/types/Instituicao/instituicao.response'
import { useRouter } from 'next/navigation'
import { TipoUsuario } from '@/types/Usuario/enum'
import { toast } from 'sonner'

export const alunoSchema = z.object({
  nome: z.string().min(1, { message: 'Informe o nome.' }),
  rg: z.string().min(1, { message: 'Informe o RG.' }),
  cpf: z.string().min(1, { message: 'Informe o CPF.' }),
  curso: z.string().min(1, { message: 'Informe o curso.' }),
  email: z.string().email({ message: 'Informe um e-mail válido.' }),
  senha: z.string().min(6, { message: 'A senha deve ter pelo menos 6 caracteres.' }),
  tipo: z.literal(TipoUsuario.ALUNO),
  instituicaoEnsino: z.number({ message: 'Selecione uma instituição de ensino.' }).optional()
})

type FormData = z.infer<typeof alunoSchema>

export default function FormAluno({ onVoltarLogin }: { onVoltarLogin: () => void }) {
  const router = useRouter()

  const form = useForm<FormData>({
    mode: 'onTouched',
    resolver: zodResolver(alunoSchema),
    defaultValues: {
      nome: '',
      rg: '',
      cpf: '',
      curso: '',
      email: '',
      senha: '',
      tipo: TipoUsuario.ALUNO,
      instituicaoEnsino: undefined
    }
  })

  const queryClient = useQueryClient()

  // Busca das instituições
  const { data: instituicoes, isLoading: carregandoInstituicoes } = useQuery({
    queryKey: ['instituicoes'],
    queryFn: async () => {
      const response = await api.get('/api/instituicoes-ensino')
      return response.data
    }
  })

  const handleSubmit = async (values: FormData) => {
    try {
      const response = await api.post('/api/alunos', values)

      if (response.status === 201 || response.status === 200) {
        queryClient.invalidateQueries({ queryKey: ['usuarios'] })
        toast.success("Cadastro realizado com sucesso! Agora você pode fazer login.");
        form.reset()
        onVoltarLogin()
      } else {
        toast.error("E-mail ou CPF já cadastrado.");
      }
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <Form {...form}>
      <p className='text-gray-500 mb-6'>Preencha o formulário abaixo para criar sua conta de aluno.</p>

      <form
        onSubmit={form.handleSubmit(handleSubmit)}
        className="flex flex-col gap-y-4 w-full"
      >
        {/* Nome */}
        <FormField
          control={form.control}
          name="nome"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Nome</FormLabel>
              <FormControl>
                <Input placeholder="Informe o nome" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* RG */}
        <FormField
          control={form.control}
          name="rg"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>RG</FormLabel>
              <FormControl>
                <Input placeholder="Informe o RG" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* CPF */}
        <FormField
          control={form.control}
          name="cpf"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>CPF</FormLabel>
              <FormControl>
                <Input placeholder="Informe o CPF" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Curso */}
        <FormField
          control={form.control}
          name="curso"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Curso</FormLabel>
              <FormControl>
                <Input placeholder="Informe o curso" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Email */}
        <FormField
          control={form.control}
          name="email"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input placeholder="Informe o e-mail" type="email" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Senha */}
        <FormField
          control={form.control}
          name="senha"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Senha</FormLabel>
              <FormControl>
                <Input placeholder="Crie uma senha" type="password" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        {/* Instituição de Ensino */}
        <FormField
          control={form.control}
          name="instituicaoEnsino"
          render={({ field }) => (
            <FormItem className="w-full">
              <FormLabel>Instituição de Ensino</FormLabel>
              <FormControl>
                <Select
                  value={field.value ? field.value.toString() : ""}
                  onValueChange={(value) => field.onChange(Number(value))}
                  disabled={carregandoInstituicoes}
                >
                  <SelectTrigger className="w-full">
                    <SelectValue placeholder="Selecione a instituição" />
                  </SelectTrigger>
                  <SelectContent>
                    {instituicoes?.map((inst: InstituicaoEnsinoResponse) => (
                      <SelectItem key={inst.id} value={inst.id.toString()}>
                        {inst.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <Button
          type="submit"
          variant={"secondary"}
          className="w-full py-6 text-lg font-semibold bg-[#1E3A8A] hover:bg-[#7B1E3A] text-white rounded-xl transition-colors cursor-pointer"
        >
          Cadastrar
        </Button>

        <div className="flex w-full text-center justify-center py-3">
          <p
            className="text-sm underline text-[#7B1E3A] hover:text-[#1E3A8A] cursor-pointer"
            onClick={onVoltarLogin}
          >
            Já tem uma conta? Fazer login
          </p>
        </div>
      </form>
    </Form>
  )
}
