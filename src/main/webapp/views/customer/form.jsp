<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>

<main class="container mx-auto px-4 py-8 flex-grow max-w-xl">
    <a href="${pageContext.request.contextPath}/customers" class="text-xs font-bold text-gray-500 hover:text-brand-purple uppercase tracking-wider inline-flex items-center mb-4 transition">
        <i class="fa-solid fa-arrow-left mr-1.5"></i> Voltar à Listagem
    </a>

    <div class="bg-white rounded-2xl shadow-sm border border-gray-200 overflow-hidden">
        
        <div class="bg-brand-black px-6 py-4 border-b border-gray-800 flex justify-between items-center">
            <h2 class="text-sm font-black uppercase tracking-wider text-white">
                <c:choose>
                    <c:when test="${customer != null}">Modificar Cliente #${customer.customerId}</c:when>
                    <c:otherwise>Novo Registro de Cliente</c:otherwise>
                </c:choose>
            </h2>
            <i class="fa-solid fa-user-tag text-brand-purple text-lg"></i>
        </div>

        <form action="${pageContext.request.contextPath}/customers?action=save" method="POST" class="p-6 space-y-4">
            
            <div>
                <label for="customerId" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Código Identificador (ID)</label>
                <c:choose>
                    <c:when test="${customer != null}">
                        <input type="number" id="customerId" name="customerId" value="${customer.customerId}" readonly 
                               class="bg-gray-100 border border-gray-300 text-gray-400 rounded-lg w-full px-3 py-2 font-mono text-sm focus:outline-none cursor-not-allowed" />
                    </c:when>
                    <c:otherwise>
                        <input type="number" id="customerId" name="customerId" required 
                               class="border border-gray-300 rounded-lg w-full px-3 py-2 font-mono text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" 
                               placeholder="Ex: 3001" />
                    </c:otherwise>
                </c:choose>
            </div>

            <div>
                <label for="custName" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Nome Completo / Empresa</label>
                <input type="text" id="custName" name="custName" value="${customer.custName}" maxlength="30" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" 
                       placeholder="Ex: Indústrias ACME Ltda" />
            </div>

            <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                    <label for="city" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Cidade Sede</label>
                    <input type="text" id="city" name="city" value="${customer.city}" maxlength="15"
                           class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" 
                           placeholder="Ex: Cubatão" />
                </div>
                
                <div>
                    <label for="grade" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Grade (Pontuação)</label>
                    <input type="number" id="grade" name="grade" value="${customer.grade}" max="999"
                           class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" 
                           placeholder="Ex: 100" />
                </div>
            </div>

            <div>
                <label for="salesmanId" class="block text-xs font-bold uppercase tracking-wide text-gray-600 mb-1">Código ID do Vendedor Responsável</label>
                <input type="number" id="salesmanId" name="salesmanId" value="${customer.salesmanId}" required
                       class="border border-gray-300 rounded-lg w-full px-3 py-2 text-sm font-mono focus:ring-2 focus:ring-brand-purple focus:border-brand-purple outline-none" 
                       placeholder="Ex: 101" />
                <p class="text-[10px] text-gray-400 mt-1">Insira o código numérico correspondente a um vendedor válido cadastrado no sistema.</p>
            </div>

            <div class="pt-4 border-t border-gray-100 flex justify-end space-x-3">
                <a href="${pageContext.request.contextPath}/customers" class="px-4 py-2 border border-gray-300 text-gray-700 rounded-lg text-xs font-bold uppercase tracking-wider hover:bg-gray-50 transition">
                    Cancelar
                </a>
                <button type="submit" class="px-4 py-2 bg-brand-purple hover:bg-brand-purple-dark text-white rounded-lg text-xs font-bold uppercase tracking-wider shadow transition">
                    <i class="fa-solid fa-floppy-disk mr-1.5"></i> Gravar Cliente
                </button>
            </div>
        </form>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>