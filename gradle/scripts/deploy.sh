aws ecr get-login-password --profile mafia --region eu-central-1 | \
  docker login --username AWS --password-stdin $ECR_URL
docker buildx build --platform=linux/amd64 -t mafia-statistics .
docker tag mafia-statistics:latest $ECR_URL/mafia-statistics:latest
docker push $ECR_URL/mafia-statistics:latest
