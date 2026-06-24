<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow">
    <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
        <div>
            <h1 class="text-2xl font-black text-brand-black tracking-tight">Carteira de Clientes</h1>
            <p class="text-xs text-gray-500">Listagem de registros mapeados pela tabela customer.</p>
        </div>
        <a href="${pageContext.request.contextPath}/views/customer/form.jsp" class="bg-brand-purple hover:bg-brand-purple-dark text-white px-4 py-2.5 rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
            <i class="fa-solid fa-plus mr-2"></i> Novo Cliente
        </a>
    </div>

    <div class="bg-white rounded-xl shadow-sm overflow-hidden border border-gray-200">
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200 text-left text-sm text-gray-700">
                <thead class="bg-brand-black text-white text-xs font-bold uppercase tracking-wider">
                    <tr>
                        <th class="px-6 py-3.5">ID Cliente</th>
                        <th class="px-6 py-3.5">Nome / Empresa</th>
                        <th class="px-6 py-3.5">Cidade Sede</th>
                        <th class="px-6 py-3.5 text-center">Grade</th>
                        <th class="px-6 py-3.5 text-center">Vendedor Vinculado</th>
                        <th class="px-6 py-3.5 text-center">Ações</th>
                    </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 bg-white">
                    <c:forEach var="cust" items="${customers}">
                        <tr class="hover:bg-purple-50/40 transition">
                            <td class="px-6 py-4 font-mono font-bold text-brand-purple">#${cust.customerId}</td>
                            <td class="px-6 py-4 font-semibold text-gray-900">${cust.custName}</td>
                            <td class="px-6 py-4 text-gray-600">${empty cust.city ? '—' : cust.city}</td>
                            <td class="px-6 py-4 text-center font-bold text-gray-700">${cust.grade}</td>
                            <td class="px-6 py-4 text-center font-mono text-xs font-bold text-brand-purple-dark">#${cust.salesmanId}</td>
                            <td class="px-6 py-4 whitespace-nowrap text-center">
                                <div class="flex justify-center items-center space-x-3">
                                    <form action="${pageContext.request.contextPath}/customers?action=delete" method="POST" onsubmit="return confirm('Excluir este cliente?');" class="inline">
                                        <input type="hidden" name="customerId" value="${cust.customerId}">
                                        <button type="submit" class="text-gray-400 hover:text-red-600 p-1.5 rounded transition">
                                            <i class="fa-solid fa-trash text-base"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty customers}">
                        <tr>
                            <td colspan="6" class="px-6 py-12 text-center text-gray-400">
                                <i class="fa-solid fa-users-slash text-4xl mb-3 block text-gray-300"></i>
                                Nenhum cliente registrado no momento.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>