"""
Models for the restaurant POS

@author Matthew Scott
@version $Id$
"""
from django.db import models
from pos.models import Employee, Product

class Restaurant(models.Model):
    geometry = models.XMLField(schema_path = 'geometry.ng')

class TableArea(models.Model):
    owner = models.ForeignKey(Employee)

class TableGroup(models.Model):
    ticket = models.ForeignKey('Ticket', blank = True, null = True)
    seats = models.IntegerField()

class Table(models.Model):
    SHAPE_CHOICES = (
            (0, "Rectangular"),
            (1, "Oval"),
            )

    name = models.CharField(max_length = 50, blank = true)
    seats = models.IntegerField()
    area = models.ForeignKey('TableArea')
    group = models.ForeignKey('TableGroup', blank = True, null = True)
    ticket = models.ForeignKey('Ticket', blank = True, null = True)
    x = models.IntegerField()
    y = models.IntegerField()
    length = models.IntegerField()
    width = models.IntegerField()
    shape = models.IntegerField(choices = SHAPE_CHOICES)
    rotation = models.IntegerField()

class Ticket(models.Model):
    PAYMENT_CHOICES = (
            (0, "Cash"),
            (1, "Check"),
            (2, "Charge"),
            )

    owner = models.ForeignKey(Employee)
    parent = models.ForeignKey('Ticket', blank = True, null = True)
    payment_method = models.IntegerField(choices = PAYMENT_CHOICES, null = True)
    paid = models.BooleanField(default = False)

class TicketItem(models.Model):
    patron = models.ForeignKey('Patron')
    ticket = models.ForeignKey('Ticket')
    product = models.ForeignKey(Product)
    price_modifier = models.DecimalField(max_digits = 5, decimal_places = 2, null = True)
    server_only = models.BooleanField(default = True)
    notes = models.TextField(blank = True)

class Patron(models.Model):
    location = models.CharField(max_length = 150, blank = True)
    notes = models.TextField()
    ticket = models.ForeignKey('Ticket')
