<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow">
    <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
        <div>
            <h1 class="text-2xl font-black text-brand-black tracking-tight">Equipe de Vendedores</h1>
        </div>
        <a href="${pageContext.request.contextPath}/salesman?action=edit&id=0" class="bg-brand-purple hover:bg-brand-purple-dark text-white px-4 py-2.5 rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
            <i class="fa-solid fa-plus mr-2"></i> Novo Vendedor
        </a>
    </div>

    <div class="bg-white rounded-xl shadow-sm overflow-hidden border border-gray-200">
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200 text-left text-sm text-gray-700">
                <thead class="bg-brand-black text-white text-xs font-bold uppercase tracking-wider">
                    <tr>
                        <th class="px-6 py-3.5">ID Vendedor</th>
                        <th class="px-6 py-3.5">Nome Completo</th>
                        <th class="px-6 py-3.5">Cidade Atuação</th>
                        <th class="px-6 py-3.5 text-right">Comissão (%)</th>
                        <th class="px-6 py-3.5 text-center">Ações</th>
                    </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 bg-white">
                    <c:forEach var="vendedor" items="${salesmen}">
                        <tr class="hover:bg-purple-50/40 transition">
                            <td class="px-6 py-4 font-mono font-bold text-brand-purple">#${vendedor.salesManId}</td>
                            <td class="px-6 py-4 font-semibold text-gray-900">${vendedor.name}</td>
                            <td class="px-6 py-4 text-gray-600">${empty vendedor.city ? 'Não Informado' : vendedor.city}</td>
                            <td class="px-6 py-4 text-right text-brand-purple font-bold">${vendedor.commission}%</td>
                            <td class="px-6 py-4 whitespace-nowrap text-center">
                                <div class="flex justify-center items-center space-x-3">
                                    <a href="${pageContext.request.contextPath}/salesman?action=edit&id=${vendedor.salesManId}" class="text-brand-black hover:text-brand-purple p-1.5 rounded transition">
                                        <i class="fa-solid fa-pen-to-square text-base"></i>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/salesman?action=delete&id=${vendedor.salesManId}" onclick="return confirm('Confirmar exclusão?');" class="text-gray-400 hover:text-red-600 p-1.5 rounded transition">
                                        <i class="fa-solid fa-trash text-base"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty salesmen}">
                        <tr>
                            <td colspan="5" class="px-6 py-12 text-center text-gray-400">
                                <i class="fa-solid fa-folder-open text-4xl mb-3 block text-gray-300"></i>
                                Nenhum vendedor retornado pelo banco.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>