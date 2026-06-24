<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow max-w-xl">
    <a href="${pageContext.request.contextPath}/orders" class="text-xs font-bold text-gray-500 hover:text-brand-purple uppercase tracking-wider inline-flex items-center mb-4 transition">
        <i class="fa-solid fa-arrow-left mr-1.5"></i> Voltar à Listagem
    </a>

    <div class="bg-white rounded-2xl shadow-sm border border-gray-200 overflow-hidden">
        <div class="bg-brand-black px-6 py-4 border-b border-gray-800 flex justify-between items-center">
            <h2 class="text-sm font-black uppercase tracking-wider text-white">
                <c:choose>
                    <c:when test="${order != null}">Atualizar Ordem Nº #${order.ordNo}</c:when>
                    <c:otherwise>Emitir Nova Ordem de Venda</c:otherwise>
                </c:choose>
            </h2>
            <i class="fa-solid fa-file-signature text-brand-purple text-lg"></i>
        </div>

        <form action="${pageContext.request.contextPath}/orders?action=save" method="POST" class="p-6 space-y-4">
            
            <div>
                <label for="ordNo" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Número da Ordem (Código Único)</label>
                <input type="number" id="ordNo" name="ordNo" value="${order.ordNo}" 
                       ${order != null ? 'readonly class="bg-gray-100 border border-gray-300 text-gray-400 rounded-lg w-full px-3 py-2 font-mono text-sm focus:outline-none cursor-not-allowed"' : 'class="border border-gray-300 rounded-lg w-full px-3 py-2 font-mono text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" required placeholder="Ex: 5001"'} />
            </div>

            <div>
                <label for="purchAmt" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Valor da Compra (R$)</label>
                <input type="number" id="purchAmt" name="purchAmt" value="${order.purchAmt}" step="0.01" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" placeholder="0.00" />
            </div>

            <div>
                <label for="ordDate" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Data da Ordem</label>
                <fmt:formatDate var="formattedDate" value="${order.ordDate}" pattern="yyyy-MM-dd"/>
                <input type="date" id="ordDate" name="ordDate" value="${formattedDate}" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" />
            </div>

            <div>
                <label for="customerId" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Cliente Adquirente</label>
                <select id="customerId" name="customerId" required class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm bg-white focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none">
                    <option value="">-- Selecione o Cliente Comprador --</option>
                    <c:forEach var="c" items="${listaClientes}">
                        <option value="${c.customerId}" ${order.customerId == c.customerId ? 'selected' : ''}>
                            ${c.custName} (ID: #${c.customerId})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="salesmanId" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Vendedor Responsável</label>
                <select id="salesmanId" name="salesmanId" required class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm bg-white focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none">
                    <option value="">-- Selecione o Vendedor Intermediário --</option>
                    <c:forEach var="v" items="${listaVendedores}">
                        <option value="${v.salesManId}" ${order.salesmanId == v.salesManId ? 'selected' : ''}>
                            ${v.name} (ID: #${v.salesManId})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="pt-4 border-t border-gray-100 flex justify-end space-x-3">
                <a href="${pageContext.request.contextPath}/orders" class="px-4 py-2 border border-gray-300 text-gray-700 rounded-lg text-xs font-bold uppercase tracking-wider hover:bg-gray-50 transition">
                    Cancelar
                </a>
                <button type="submit" class="px-4 py-2 bg-brand-purple hover:bg-brand-purple-dark text-white rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
                    <i class="fa-solid fa-floppy-disk mr-1.5"></i> Salvar Ordem
                </button>
            </div>
        </form>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>