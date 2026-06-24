<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp" %>

<main class="container mx-auto px-4 py-10 flex-grow">
    <div class="bg-brand-black text-white rounded-2xl shadow-xl p-8 md:p-12 mb-10 relative overflow-hidden">
        <div class="absolute right-0 top-0 opacity-10 text-9xl transform translate-x-10 -translate-y-10 text-brand-purple">
            <i class="fa-solid fa-laptop-code"></i>
        </div>
        <span class="bg-brand-purple text-xs uppercase tracking-widest font-extrabold px-3 py-1 rounded-full text-white">Projeto Acadêmico</span>
        <h1 class="text-3xl md:text-4xl font-black mt-4 tracking-tight">Painel de Controle de Vendas</h1>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        
        <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6 flex flex-col justify-between hover:border-brand-purple transition duration-300">
            <div>
                <div class="w-12 h-12 bg-purple-50 text-brand-purple rounded-xl flex items-center justify-center text-xl font-bold mb-4">
                    <i class="fa-solid fa-user-tie"></i>
                </div>
                <h3 class="text-lg font-bold text-brand-black">Vendedores</h3>
                <p class="text-gray-500 text-xs mt-1">Gerencie o cadastro, remoção e monitoramento das taxas de comissão da equipe.</p>
            </div>
            <a href="${pageContext.request.contextPath}/salesman" class="mt-6 w-full text-center bg-brand-black hover:bg-brand-purple text-white font-bold text-xs py-2.5 rounded-lg uppercase tracking-wider transition">
                Gerenciar Equipe
            </a>
        </div>

        <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6 flex flex-col justify-between hover:border-brand-purple transition duration-300">
            <div>
                <div class="w-12 h-12 bg-purple-50 text-brand-purple rounded-xl flex items-center justify-center text-xl font-bold mb-4">
                    <i class="fa-solid fa-address-book"></i>
                </div>
                <h3 class="text-lg font-bold text-brand-black">Clientes</h3>
                <p class="text-gray-500 text-xs mt-1">Cadastre clientes corporativos e associe-os à sua respectiva grade de classificação.</p>
            </div>
            <a href="${pageContext.request.contextPath}/customers" class="mt-6 w-full text-center bg-brand-black hover:bg-brand-purple text-white font-bold text-xs py-2.5 rounded-lg uppercase tracking-wider transition">
                Gerenciar Clientes
            </a>
        </div>

        <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-6 flex flex-col justify-between hover:border-brand-purple transition duration-300">
            <div>
                <div class="w-12 h-12 bg-purple-50 text-brand-purple rounded-xl flex items-center justify-center text-xl font-bold mb-4">
                    <i class="fa-solid fa-file-invoice-dollar"></i>
                </div>
                <h3 class="text-lg font-bold text-brand-black">Ordens de Venda</h3>
                <p class="text-gray-500 text-xs mt-1">Gere novos pedidos conectando dinamicamente um comprador a um vendedor corporativo.</p>
            </div>
            <a href="${pageContext.request.contextPath}/orders" class="mt-6 w-full text-center bg-brand-black hover:bg-brand-purple text-white font-bold text-xs py-2.5 rounded-lg uppercase tracking-wider transition">
                Gerenciar Ordens
            </a>
        </div>

    </div>
</main>

<%@ include file="includes/footer.jsp" %>