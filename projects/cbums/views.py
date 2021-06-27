from django.shortcuts import render, get_object_or_404
# Create your views here.
def defaultPage(request):
    """
        기본 페이지
    """
    return render(request, 'cbums/default.html')