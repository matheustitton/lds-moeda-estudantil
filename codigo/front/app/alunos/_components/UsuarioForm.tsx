'use client'

import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQuery, useQueryClient } from '@tanstack/react-query'
import { Form, FormField, FormItem, FormLabel, FormControl, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Button } from '@/components/ui/button'
import { TipoUsuario } from '@/types/Usuario/usuario.request'
import api from '@/lib/axios'

const alunoSchema = z.object({
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

export default function FormAluno() {
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
      const response = await api.get('http://localhost:8080/api/instituicoes-ensino')
      console.log(response);
      return response.data
    }
  })

  const handleSubmit = async (values: FormData) => {
    console.log(values)
    try {
      const response = await api.post('http://localhost:8080/api/alunos', values)

      if (response.status === 201 || response.status === 200) {
        queryClient.invalidateQueries({ queryKey: ['usuarios'] })
        alert('Aluno cadastrado com sucesso!')
        form.reset()
      }
    } catch (error) {
      console.error(error)
      alert('Erro ao cadastrar aluno.')
    }
  }

  return (
    <Form {...form}>
      <form
        onSubmit={form.handleSubmit(handleSubmit)}
        className="flex flex-col justify-center items-center-safe gap-y-4 w-md"
      >
        <h1 className="font-bold text-2xl">Cadastro de Aluno</h1>

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
                  value={field.value ? field.value.toString() : ''}
                  onValueChange={(value) => field.onChange(Number(value))}
                  disabled={carregandoInstituicoes}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a instituição" />
                  </SelectTrigger>
                  <SelectContent>
                    {instituicoes?.map((inst: any) => (
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

        <Button type="submit" className="mt-4">
          Cadastrar
        </Button>
      </form>
    </Form>
  )
}
