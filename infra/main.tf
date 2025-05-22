
# script de Terraform que despliega una infraestructura en AWS

# Configuración Inicial de Terraform


terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}


# Base de Datos PostgreSQL (RDS)

resource "aws_db_instance" "franchise_db" {
  identifier           = "franchise-db"
  engine              = "postgres"
  engine_version      = "14.4"
  instance_class      = "db.t3.micro"
  allocated_storage   = 20
  username           = "postgres"
  password           = "yoursecurepassword"
  publicly_accessible = true
  skip_final_snapshot = true
}


#  Repositorio de Imágenes Docker (ECR)

resource "aws_ecr_repository" "franchise_app" {
  name = "franchise-management-app"
}

resource "aws_ecs_cluster" "franchise_cluster" {
  name = "franchise-cluster"
}


# Definición de Tarea ECS (Task Definition)

resource "aws_ecs_task_definition" "franchise_task" {
  family                   = "franchise-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([{
    name      = "franchise-app"
    image     = "${aws_ecr_repository.franchise_app.repository_url}:latest"
    essential = true
    portMappings = [{
      containerPort = 8080
      hostPort      = 8080
    }]
    environment = [
      {
        name  = "SPRING_R2DBC_URL"
        value = "r2dbc:postgresql://${aws_db_instance.franchise_db.endpoint}/franchisedb"
      },
      {
        name  = "SPRING_R2DBC_USERNAME"
        value = "postgres"
      },
      {
        name  = "SPRING_R2DBC_PASSWORD"
        value = "yoursecurepassword"
      }
    ]
  }])
}



# Servicio ECS (Despliegue Continuo)

resource "aws_ecs_service" "franchise_service" {
  name            = "franchise-service"
  cluster         = aws_ecs_cluster.franchise_cluster.id
  task_definition = aws_ecs_task_definition.franchise_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = ["subnet-12345678"] # Reemplaza con tus subnets
    security_groups  = ["sg-12345678"]     # Reemplaza con tus security groups
    assign_public_ip = true
  }
}