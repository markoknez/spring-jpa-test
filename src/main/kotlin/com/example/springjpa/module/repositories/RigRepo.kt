package com.example.springjpa.module.repositories

import com.example.springjpa.module.Rig
import com.example.springjpa.module.RigId

import org.springframework.data.repository.CrudRepository

interface RigRepo : CrudRepository<Rig, RigId>