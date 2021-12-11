aws ecr get-login-password --region eu-central-1 | \
  docker login --username AWS --password-stdin $ECR_URL
docker build -t mafia-statistics .
docker tag mafia-statistics:latest $ECR_URL/mafia-statistics:latest
docker push $ECR_URL/mafia-statistics:latest