<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SalesApp</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        brand: {
                            black: '#121214',
                            purple: {
                                light: '#9867f0',
                                DEFAULT: '#7c3aed',
                                dark: '#5b21b6'
                            }
                        }
                    }
                }
            }
        }
    </script>
</head>
<body class="bg-gray-50 text-brand-black flex flex-col min-h-screen font-sans">

    <nav class="bg-brand-black text-white shadow-md border-b-2 border-brand-purple">
        <div class="container mx-auto px-4 py-3 flex justify-between items-center">
            <div class="flex items-center space-x-3">
                <div class="bg-brand-purple p-2 rounded-lg text-white">
                    <i class="fa-solid fa-cart-shopping text-xl"></i>
                </div>
                <div>
                    <span class="font-extrabold text-lg block tracking-wider uppercase text-white">SalesApp</span>
                </div>
            </div>
            <div class="flex space-x-6 font-semibold text-sm tracking-wide">
                <a href="${pageContext.request.contextPath}/views/index.jsp" class="hover:text-brand-purple-light transition">Início</a>
                <a href="${pageContext.request.contextPath}/salesman" class="hover:text-brand-purple-light transition">Vendedores</a>
                <a href="${pageContext.request.contextPath}/customers" class="hover:text-brand-purple-light transition">Clientes</a>
                <a href="${pageContext.request.contextPath}/orders" class="hover:text-brand-purple-light transition">Ordens</a>
            </div>
        </div>
    </nav>