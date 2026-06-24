<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow">
    <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
        <div>
            <h1 class="text-2xl font-black text-brand-black tracking-tight">Ordens de Venda</h1>
            <p class="text-xs text-gray-500">Histórico completo de transações e ordens comerciais emitidas.</p>
        </div>
        <a href="${pageContext.request.contextPath}/orders?action=new" class="bg-brand-purple hover:bg-brand-purple-dark text-white px-4 py-2.5 rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
            <i class="fa-solid fa-plus mr-2"></i> Nova Ordem
        </a>
    </div>

    <div class="bg-white rounded-xl shadow-sm overflow-hidden border border-gray-200">
        <div class="overflow-x-auto">
            <table class="min-w-full divide-y divide-gray-200 text-left text-sm text-gray-700">
                <thead class="bg-brand-black text-white text-xs font-bold uppercase tracking-wider">
                    <tr>
                        <th class="px-6 py-3.5">Nº Ordem</th>
                        <th class="px-6 py-3.5">Data Emissão</th>
                        <th class="px-6 py-3.5 text-center">ID Cliente</th>
                        <th class="px-6 py-3.5 text-center">ID Vendedor</th>
                        <th class="px-6 py-3.5 text-right">Valor Adquirido</th>
                        <th class="px-6 py-3.5 text-center">Gestão</th>
                    </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 bg-white">
                    <c:forEach var="ord" items="${orders}">
                        <tr class="hover:bg-purple-50/40 transition">
                            <td class="px-6 py-4 font-mono font-bold text-brand-black">#${ord.ordNo}</td>
                            <td class="px-6 py-4 text-gray-600">
                                <fmt:formatDate value="${ord.ordDate}" pattern="dd/MM/yyyy"/>
                            </td>
                            <td class="px-6 py-4 text-center font-mono font-semibold text-gray-700">#${ord.customerId}</td>
                            <td class="px-6 py-4 text-center font-mono font-semibold text-brand-purple">#${ord.salesmanId}</td>
                            <td class="px-6 py-4 text-right font-bold text-gray-900">
                                R$ <fmt:formatNumber value="${ord.purchAmt}" minFractionDigits="2" maxFractionDigits="2"/>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-center">
                                <div class="flex justify-center items-center space-x-3">
                                    <a href="${pageContext.request.contextPath}/orders?action=edit&id=${ord.ordNo}" class="text-brand-black hover:text-brand-purple p-1.5 rounded transition">
                                        <i class="fa-solid fa-pen-to-square text-base"></i>
                                    </a>
                                    <form action="${pageContext.request.contextPath}/orders?action=delete" method="POST" onsubmit="return confirm('Anular esta ordem permanentemente?');" class="inline">
                                        <input type="hidden" name="ordNo" value="${ord.ordNo}">
                                        <button type="submit" class="text-gray-400 hover:text-red-600 p-1.5 rounded transition">
                                            <i class="fa-solid fa-trash text-base"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty orders}">
                        <tr>
                            <td colspan="6" class="px-6 py-12 text-center text-gray-400">
                                <i class="fa-solid fa-receipt text-4xl mb-3 block text-gray-300"></i>
                                Nenhuma ordem de venda registada.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>