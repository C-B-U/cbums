# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models

# Create your models here.

class TbBoard(models.Model):
    board_name = models.CharField(primary_key=True, max_length=45)
    board_producer = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='board_producer')
    board_production_dt = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'TB_board'


class TbBoardSubscription(models.Model):
    board_name = models.OneToOneField(TbBoard, models.DO_NOTHING, db_column='board_name', primary_key=True)
    subscriber = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='subscriber')
    subscription_dt = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'TB_board_subscription'
        unique_together = (('board_name', 'subscriber'),)
        
class TbBook(models.Model):
    isbn = models.IntegerField(primary_key=True)
    book_owner = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='book_owner', related_name='book_owner')
    book_name = models.CharField(max_length=45)
    book_borrower = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='book_borrower', blank=True, null=True)
    book_rg_dt = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'TB_book'
        unique_together = (('isbn', 'book_owner'),)


class TbBookTag(models.Model):
    isbn = models.OneToOneField(TbBook, models.DO_NOTHING, db_column='isbn', primary_key=True)
    book_owner = models.ForeignKey(TbBook, models.DO_NOTHING, db_column='book_owner', related_name='tag_book_owner')
    tag_name = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_name')

    class Meta:
        managed = False
        db_table = 'TB_book_tag'
        unique_together = (('isbn', 'book_owner', 'tag_name'),)

class TbComment(models.Model):
    comment_seq = models.AutoField(primary_key=True)
    post_seq = models.ForeignKey('TbPost', models.DO_NOTHING, db_column='post_seq')
    comment_content = models.CharField(max_length=3000)
    comment_writer = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='comment_writer')
    comment_likes_count = models.IntegerField()
    comment_rg_dt = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'TB_comment'


class TbForm(models.Model):
    form_seq = models.AutoField(primary_key=True)
    form_title = models.CharField(max_length=45)
    form_introduce = models.CharField(max_length=1500, blank=True, null=True)
    form_start_dt = models.DateTimeField()
    form_end_dt = models.DateTimeField()
    rg_no = models.IntegerField(blank=True, null=True)
    producer = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='producer')

    class Meta:
        managed = False
        db_table = 'TB_form'
        
class TbFormAnswer(models.Model):
    form_seq = models.IntegerField(primary_key=True)
    question_seq = models.IntegerField()
    form_writer = models.CharField(max_length=40)
    answer_content = models.CharField(max_length=6000)

    class Meta:
        managed = False
        db_table = 'TB_form_answer'
        unique_together = (('form_seq', 'question_seq', 'form_writer'),)


class TbFormContent(models.Model):
    form_seq = models.OneToOneField(TbForm, models.DO_NOTHING, db_column='form_seq', primary_key=True)
    question_seq = models.ForeignKey('TbFormQuestion', models.DO_NOTHING, db_column='question_seq')

    class Meta:
        managed = False
        db_table = 'TB_form_content'
        unique_together = (('form_seq', 'question_seq'),)


class TbFormQuestion(models.Model):
    question_seq = models.AutoField(primary_key=True)
    question_content = models.CharField(max_length=100)
    question_producer = models.ForeignKey('TbMember', models.DO_NOTHING, db_column='question_producer')
    producer_dt = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'TB_form_question'
        
class TbMember(models.Model):
    m_email = models.CharField(primary_key=True, max_length=40)
    password = models.CharField(max_length=64)
    m_name = models.CharField(max_length=12)
    m_rg_no = models.IntegerField(blank=True, null=True)
    class_no = models.IntegerField()
    department = models.CharField(max_length=15)
    m_image = models.CharField(max_length=45, blank=True, null=True)
    sysop_status = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'TB_member'


class TbMemberTag(models.Model):
    m_email = models.OneToOneField(TbMember, models.DO_NOTHING, db_column='m_email', primary_key=True)
    tag_name = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_name')

    class Meta:
        managed = False
        db_table = 'TB_member_tag'
        unique_together = (('m_email', 'tag_name'),)
        
class TbPost(models.Model):
    post_seq = models.AutoField(primary_key=True)
    board_name = models.ForeignKey(TbBoard, models.DO_NOTHING, db_column='board_name')
    post_title = models.CharField(max_length=150)
    post_content = models.CharField(max_length=9000)
    post_image = models.CharField(max_length=45, blank=True, null=True)
    post_rg_dt = models.DateTimeField()
    post_writer = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='post_writer', blank=True, null=True)
    post_likes_count = models.IntegerField()
    post_visits_count = models.IntegerField()
    parent_post_seq = models.ForeignKey('self', models.DO_NOTHING, db_column='parent_post_seq', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'TB_post'


class TbPostTag(models.Model):
    post_seq = models.OneToOneField(TbPost, models.DO_NOTHING, db_column='post_seq', primary_key=True)
    tag_name = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_name')

    class Meta:
        managed = False
        db_table = 'TB_post_tag'
        unique_together = (('post_seq', 'tag_name'),)
        
class TbProject(models.Model):
    p_seq = models.AutoField(primary_key=True)
    p_name = models.CharField(max_length=45)
    p_rg_dt = models.DateTimeField()
    maximum_m = models.IntegerField(blank=True, null=True)
    project_producer = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='project_producer')
    producer_hidden = models.IntegerField()
    project_image = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'TB_project'


class TbProjectMember(models.Model):
    p_seq = models.OneToOneField(TbProject, models.DO_NOTHING, db_column='p_seq', primary_key=True)
    p_m = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='p_m')
    m_role = models.CharField(max_length=30, blank=True, null=True)
    sign_up_dt = models.DateTimeField()
    sign_up_status = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'TB_project_member'
        unique_together = (('p_seq', 'p_m'),)
        
class TbProjectParticipation(models.Model):
    p_seq = models.OneToOneField('TbProjectProgress', models.DO_NOTHING, db_column='p_seq', primary_key=True)
    progress_date = models.ForeignKey('TbProjectProgress', models.DO_NOTHING, db_column='progress_date', related_name='p_progress_date')
    p_m = models.ForeignKey(TbProjectMember, models.DO_NOTHING, db_column='p_m')
    attend_status = models.IntegerField()
    homework_submit_status = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'TB_project_participation'
        unique_together = (('p_seq', 'progress_date', 'p_m'),)


class TbProjectPlan(models.Model):
    p_seq = models.OneToOneField(TbProject, models.DO_NOTHING, db_column='p_seq', primary_key=True)
    plan_date = models.DateField()
    plan_content = models.CharField(max_length=100)
    homework = models.CharField(max_length=100, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'TB_project_plan'
        unique_together = (('p_seq', 'plan_date'),)

class TbProjectProgress(models.Model):
    p_seq = models.OneToOneField(TbProject, models.DO_NOTHING, db_column='p_seq', primary_key=True)
    progress_date = models.DateField()
    progress_content = models.CharField(max_length=6000)
    homework = models.CharField(max_length=100, blank=True, null=True)
    progress_image = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'TB_project_progress'
        unique_together = (('p_seq', 'progress_date'),)


class TbProjectTag(models.Model):
    p_seq = models.OneToOneField(TbProject, models.DO_NOTHING, db_column='p_seq', primary_key=True)
    tag_name = models.ForeignKey('TbTag', models.DO_NOTHING, db_column='tag_name')

    class Meta:
        managed = False
        db_table = 'TB_project_tag'
        unique_together = (('p_seq', 'tag_name'),)


class TbTag(models.Model):
    tag_name = models.CharField(primary_key=True, max_length=24)
    tag_production_dt = models.DateTimeField()
    producer = models.ForeignKey(TbMember, models.DO_NOTHING, db_column='producer')

    class Meta:
        managed = False
        db_table = 'TB_tag'
