<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow max-w-xl">
    <a href="${pageContext.request.contextPath}/salesman" class="text-xs font-bold text-gray-500 hover:text-brand-purple uppercase tracking-wider inline-flex items-center mb-4 transition">
        <i class="fa-solid fa-arrow-left mr-1.5"></i> Voltar à Listagem
    </a>

    <div class="bg-white rounded-2xl shadow-sm border border-gray-200 overflow-hidden">
        <div class="bg-brand-black px-6 py-4 border-b border-gray-800 flex justify-between items-center">
            <h2 class="text-sm font-black uppercase tracking-wider text-white">
                <c:choose>
                    <c:when test="${salesMan != null}">Modificar Vendedor #${salesMan.salesManId}</c:when>
                    <c:otherwise>Novo Registro de Vendedor</c:otherwise>
                </c:choose>
            </h2>
            <i class="fa-solid fa-user-tie text-brand-purple text-lg"></i>
        </div>

        <form action="${pageContext.request.contextPath}/salesman?action=save" method="POST" class="p-6 space-y-4">
            
            <div>
                <label for="salesmanId" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Código ID</label>
                <input type="number" id="salesmanId" name="salesmanId" value="${salesMan.salesManId}" 
                       ${salesMan != null ? 'readonly class="bg-gray-100 border border-gray-300 text-gray-400 rounded-lg w-full px-3 py-2 font-mono text-sm focus:outline-none cursor-not-allowed"' : 'class="border border-gray-300 rounded-lg w-full px-3 py-2 font-mono text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" placeholder="Deixe em branco para auto-gerar"'} />
            </div>

            <div>
                <label for="name" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Nome Completo</label>
                <input type="text" id="name" name="name" value="${salesMan.name}" maxlength="30" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" placeholder="Ex: Carlos Alberto" />
            </div>

            <div>
                <label for="city" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Cidade de Atuação</label>
                <input type="text" id="city" name="city" value="${salesMan.city}" maxlength="15"
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" placeholder="Ex: Santos" />
            </div>

            <div>
                <label for="commission" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Percentual de Comissão (%)</label>
                <input type="number" id="commission" name="commission" value="${salesMan.commission}" step="0.01" max="999.99" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" placeholder="Ex: 12.50" />
            </div>

            <div class="pt-4 border-t border-gray-100 flex justify-end space-x-3">
                <a href="${pageContext.request.contextPath}/salesman" class="px-4 py-2 border border-gray-300 text-gray-700 rounded-lg text-xs font-bold uppercase tracking-wider hover:bg-gray-50 transition">
                    Cancelar
                </a>
                <button type="submit" class="px-4 py-2 bg-brand-purple hover:bg-brand-purple-dark text-white rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
                    <i class="fa-solid fa-floppy-disk mr-1.5"></i> Gravar Vendedor
                </button>
            </div>
        </form>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>